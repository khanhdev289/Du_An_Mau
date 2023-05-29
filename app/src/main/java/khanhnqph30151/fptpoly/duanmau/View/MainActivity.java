package khanhnqph30151.fptpoly.duanmau.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.fragment.DoanhThuFragment;
import khanhnqph30151.fptpoly.duanmau.fragment.DoiMatKhauFragment;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSachFragment;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyPhieuMuonFragment;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLySachFragment;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyThanhVienFragment;
import khanhnqph30151.fptpoly.duanmau.fragment.Top10SachMuonNhieuNhatFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.id_drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, 0, 0);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.id_nav);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new QuanLySachFragment());


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isOpen()) {
            drawerLayout.close();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        setTitle(item.getTitle());
        if (item.getItemId() == R.id.qlphieumuon) {
            drawerLayout.close();
            replaceFragment(QuanLyPhieuMuonFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.qlloaisach) {
            drawerLayout.close();
            replaceFragment(QuanLyLoaiSachFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.qlsach) {
            drawerLayout.close();
            replaceFragment(QuanLySachFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.qlthanhvien) {
            drawerLayout.close();
            replaceFragment(QuanLyThanhVienFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.top10) {
            drawerLayout.close();
            replaceFragment(Top10SachMuonNhieuNhatFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.doanhthu) {
            drawerLayout.close();
            replaceFragment(DoanhThuFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.doimk) {
            drawerLayout.close();
            replaceFragment(DoiMatKhauFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.thoat) {
            finish();
            return true;
        } else {
            return false;
        }


    }


    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }
}