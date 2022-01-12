package br.com.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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

    private class ListCalcAdapter extends RecyclerView.Adapter<ListCalcHolder> {

        private final List<Register> registers;

        public ListCalcAdapter(List<Register> registers) {
            this.registers = registers;
        }

        @NonNull
        @Override
        public ListCalcHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ListCalcHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ListCalcHolder holder, int position) {
            holder.bind(registers.get(position));
        }

        @Override
        public int getItemCount() {
            return registers.size();
        }
    }

    private static class ListCalcHolder extends RecyclerView.ViewHolder {

        public ListCalcHolder(@NonNull View itemView) {
            super(itemView);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Register register){
            TextView title = itemView.findViewById(R.id.list_calc_title);
            TextView date = itemView.findViewById(R.id.list_calc_date);

            title.setText(register.getResult()+"");
            date.setText(register.getCreatedDate());

        }


    }
}

