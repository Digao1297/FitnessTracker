package br.com.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListCalcActivity extends AppCompatActivity {

    private List<Register> registers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_calc);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String type = extras.getString("type");


            registers = SqlHelper.getInstance(this).getRegisterByType(type);

            _recycleViewInit();

        }
    }

    void _recycleViewInit() {
        RecyclerView recyclerView = findViewById(R.id.list_calc_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListCalcAdapter(registers));
    }

    private class ListCalcAdapter extends RecyclerView.Adapter<ListCalcAdapter.ListCalcHolder> {

        private final List<Register> registers;

        public ListCalcAdapter(List<Register> registers) {
            this.registers = registers;
        }

        @NonNull
        @Override
        public ListCalcHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ListCalcHolder(getLayoutInflater().inflate(R.layout.list_calc_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ListCalcHolder holder, int position) {
            holder.bind(registers.get(position));
        }

        @Override
        public int getItemCount() {
            return registers.size();
        }

        private class ListCalcHolder extends RecyclerView.ViewHolder {

            public ListCalcHolder(@NonNull View itemView) {
                super(itemView);
            }


            public void bind(Register register) {
                TextView title = itemView.findViewById(R.id.list_calc_title);
                TextView date = itemView.findViewById(R.id.list_calc_date);
                String formatted = "";

                title.setText(getString(R.string.imc_response, register.getResult()));

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("pt","br"));
                    Date dateSaved = sdf.parse(register.getCreatedDate());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
                     formatted = dateFormat.format(dateSaved);

                } catch (ParseException e){

                }

                date.setText(formatted);

            }


        }

    }


}

