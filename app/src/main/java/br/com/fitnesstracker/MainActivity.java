package br.com.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private View btnImc;

    private RecyclerView rvMain;
    private List<MainItem> mainItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnImc = findViewById(R.id.btn_imc);
//
//        btnImc.setOnClickListener((view) -> {
//                Intent intent = new Intent(MainActivity.this, ImcActivity.class);
//                startActivity(intent);
//        });

        rvMain = findViewById(R.id.main_rv);
        mainItems = new ArrayList<>();

        mainItems.add(new MainItem(1, R.drawable.ic_baseline_accessibility_new_24,  Color.GREEN,R.string.label_imc));
        mainItems.add(new MainItem(2, R.drawable.ic_baseline_visibility_24,  Color.RED,R.string.label_tmb));


        // definir o comportamento de exibição do layout da recycleView
        // mosaic
        // grid
        // linear (horizontal | vertical)
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter adapter = new MainAdapter(mainItems);
        rvMain.setAdapter(adapter);

    }

    private class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

        private List<MainItem> items;

        public MainAdapter(List<MainItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return mainItems.size();
        }
    }

    // responsavel pela VIEW da celula que está dentro da recycleView
    private class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(MainItem item) {
            ImageView imageView = itemView.findViewById(R.id.main_item_img);
            TextView textView = itemView.findViewById(R.id.main_item_text);
            LinearLayout container = (LinearLayout) itemView;

            textView.setText(item.getTextId());
            imageView.setImageResource(item.getDrawableId());
            container.setBackgroundColor(item.getColor());
        }
    }
}