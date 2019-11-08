package radinyazilim.com.mhfz.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import radinyazilim.com.mhfz.R;
import radinyazilim.com.mhfz.models.EmployeeModel;





public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {

    ItemListener listener;

    List<EmployeeModel> note_list;
    Context context;

    public RecyclerViewAdaptor(Context context, List<EmployeeModel> note_list) {

        this.context = context;
        this.note_list = note_list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nameSurname;
        public TextView employeeId;
        public ImageView avatar;
        EmployeeModel employee;
        //ItemListener listener;


        public ViewHolder(View view) {
            super(view);

            nameSurname = view.findViewById(R.id.metin);
            employeeId =  view.findViewById(R.id.ID);
            avatar = view.findViewById(R.id.avatar);
            view.setOnClickListener(this);

        }
        public void bindItem(EmployeeModel employee){
            this.employee = employee;
            employeeId.setText(employee.employeeId);
            nameSurname.setText(employee.nameSurname);
        }


        @SuppressLint("ResourceAsColor")
        public void bindBoldItem(EmployeeModel employee){
            this.employee = employee;
            employeeId.setText(employee.employeeId);
            employeeId.setTextColor(R.color.soluk_sari);
            nameSurname.setText(employee.nameSurname);
            nameSurname.setTextColor(R.color.soluk_sari);
            avatar.setImageResource(R.drawable.error);
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onItemClicked(employee);
            }
        }
    }


    @Override
    public RecyclerViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.isci_goruntu, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onItemClick(v, view_holder.getPosition());
            }
        });*/

        return view_holder;
    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        EmployeeModel employee = note_list.get(position);
        if(employee.alert ==1){
            holder.bindBoldItem(employee);
        }
        else {
            holder.bindItem(employee);
        }
    }

    @Override
    public int getItemCount() {
        return note_list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public interface ItemListener{
        public void onItemClicked(EmployeeModel employee);
    }

    public void setItemListener(ItemListener listener){
        this.listener = listener;
    }



    public void updateData(List<EmployeeModel> employeeModels){

        note_list = employeeModels;
        notifyDataSetChanged();
    }
}