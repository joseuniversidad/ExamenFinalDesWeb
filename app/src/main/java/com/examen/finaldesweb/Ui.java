package com.examen.finaldesweb;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.*;

final class Ui {
    static final int GREEN = Color.rgb(36, 107, 87);
    static int dp(Activity a, int value) { return (int)(value * a.getResources().getDisplayMetrics().density); }
    static LinearLayout screen(Activity a, String title, String subtitle) {
        LinearLayout root = new LinearLayout(a);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(a, 24), dp(a, 24), dp(a, 24), dp(a, 16));
        root.setBackgroundColor(Color.rgb(245, 247, 244));
        TextView brand = text(a, "MI JORNADA  /  TAREAS", 12);
        brand.setTextColor(GREEN);
        root.addView(brand);
        root.addView(text(a, title, 30));
        root.addView(text(a, subtitle, 15));
        a.setContentView(root);
        return root;
    }
    static TextView text(Activity a, String value, int size) {
        TextView t = new TextView(a);
        t.setText(value); t.setTextSize(size); t.setTextColor(Color.rgb(30, 48, 41));
        t.setPadding(0, dp(a, 8), 0, dp(a, 8));
        return t;
    }
    static Button button(Activity a, LinearLayout root, String label, View.OnClickListener action) {
        Button b = new Button(a); b.setText(label); b.setAllCaps(false);
        b.setTextColor(GREEN); b.setOnClickListener(action); root.addView(b); return b;
    }
}
