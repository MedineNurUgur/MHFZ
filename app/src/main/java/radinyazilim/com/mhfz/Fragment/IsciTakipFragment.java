package radinyazilim.com.mhfz.Fragment;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import radinyazilim.com.mhfz.Activities.EmployeeDetailActivity;
import radinyazilim.com.mhfz.Adapter.RecyclerViewAdaptor;
import radinyazilim.com.mhfz.Api.ApiClient;
import radinyazilim.com.mhfz.Api.EmployeeApiClient;
import radinyazilim.com.mhfz.Api.EmployeeRestInterface;
import radinyazilim.com.mhfz.R;
import radinyazilim.com.mhfz.models.EmployeeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class IsciTakipFragment extends Fragment implements RecyclerViewAdaptor.ItemListener {

    private List<EmployeeModel> employeeList=new ArrayList<EmployeeModel>();
    private List<EmployeeModel> empList = new ArrayList<>();
    private RecyclerView recycler_view;
    EmployeeRestInterface restInterface;
    RecyclerViewAdaptor adaptor_items;
    LinearLayoutManager layoutManager;
    RecyclerViewAdaptor.ItemListener listener;
    private static final String TAG = "Mhfz-> ";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_isci_takip, null);
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        listener= this;


        restInterface = ApiClient.getClient().create(EmployeeRestInterface.class);
        Call<List<EmployeeModel>> call = restInterface.getEmployeeModel();
        call.enqueue(new Callback<List<EmployeeModel>>() {
            @Override
            public void onResponse(Call<List<EmployeeModel>> call, Response<List<EmployeeModel>> response) {
                employeeList = response.body();
                for(int i =0 ;i<employeeList.size();i++){
                    if(employeeList.get(i).alert == 1){
                        empList.add(employeeList.get(i));
                        employeeList.remove(i);
                    }
                }
                for(int i =0 ;i<employeeList.size();i++){
                    empList.add(employeeList.get(i));
                }
                layoutManager = new LinearLayoutManager(view.getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                layoutManager.scrollToPosition(0);
                recycler_view.setLayoutManager(layoutManager);

                adaptor_items = new RecyclerViewAdaptor(getContext(), empList);
                adaptor_items.setItemListener(listener);
                recycler_view.setAdapter(adaptor_items);
                recycler_view.setHasFixedSize(true);
                recycler_view.setItemAnimator(new DefaultItemAnimator());

            }

            @Override
            public void onFailure(Call<List<EmployeeModel>> call, Throwable t) {


            }
        });




        return view;

    }
    @Override
    public void onItemClicked(final EmployeeModel employee) {
        if(employee.alert == 1){
            employee.alert=0;
            restInterface = EmployeeApiClient.getClient().create(EmployeeRestInterface.class);
            Call<EmployeeModel> call = restInterface.createPost(employee);
            call.enqueue(new Callback<EmployeeModel>() {
                @Override
                public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<EmployeeModel> call, Throwable t) {

                }
            });

        }
        DecimalFormat df=new DecimalFormat("#.##");

        Intent intent = new Intent(getContext(), EmployeeDetailActivity.class);
        intent.putExtra("Name",employee.nameSurname);
        intent.putExtra("Id",employee.employeeId);
        intent.putExtra("Time",df.format(employee.takilmayanSure));
        intent.putExtra("Puan",employee.puan.toString());
        startActivity(intent);





    }
    @Override
    public void onPause(){
        final List<EmployeeModel> list = new ArrayList<>();
        super.onPause();
        restInterface = ApiClient.getClient().create(EmployeeRestInterface.class);
        Call<List<EmployeeModel>> call = restInterface.getEmployeeModel();
        call.enqueue(new Callback<List<EmployeeModel>>() {
            @Override
            public void onResponse(Call<List<EmployeeModel>> call, Response<List<EmployeeModel>> response) {
                employeeList = response.body();
                for(int i =0 ;i<employeeList.size();i++){
                    if(employeeList.get(i).alert == 1){
                        list.add(employeeList.get(i));
                        employeeList.remove(i);
                    }
                }
                for(int i =0 ;i<employeeList.size();i++){
                    list.add(employeeList.get(i));
                }
                adaptor_items.updateData(empList);
                recycler_view.setAdapter(adaptor_items);
                recycler_view.setHasFixedSize(true);
                recycler_view.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<List<EmployeeModel>> call, Throwable t) {


            }
        });

    }
}
