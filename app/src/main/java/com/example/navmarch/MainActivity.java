package com.example.navmarch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.MediaStore;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "My toast", Toast.LENGTH_SHORT).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(camera);
        } else if (id == R.id.nav_gallery) {
            Intent phonecall = new Intent(Intent.ACTION_DIAL);
            phonecall.setData(Uri.parse("tel:+254711479675"));
            startActivity(phonecall);

        } else if (id == R.id.nav_slideshow) {
            Uri myno=Uri.parse("smsto:+254711479675"); //declaring a uri variable to store parsed phone no
            Intent sms = new Intent(Intent.ACTION_SENDTO,myno);
            sms.putExtra("body", "My day is");
            startActivity(sms);

        } else if (id == R.id.nav_tools) {
            Intent share= new Intent(Intent.ACTION_SEND);
            share.setType("plain/text");
            share.putExtra(Intent.EXTRA_EMAIL,new String[]{"joemaish15@gmail.com"});
            share.putExtra(Intent.EXTRA_SUBJECT,new String[]{"Welcome"});
            startActivity(Intent.createChooser(share, "Feedback"));

        } else if (id == R.id.nav_share) {
            Intent share= new Intent(Intent.ACTION_SEND);
            share.setType("plain/text");
            share.putExtra(Intent.EXTRA_SUBJECT,new String[]{""});
            share.putExtra(Intent.EXTRA_TEXT,new String[]{""});
            startActivity(Intent.createChooser(share, "Feedback"));

        } else if (id == R.id.nav_send) {
        Intent simtoolkit = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.android.stk");

        if(simtoolkit != null){
            startActivity(simtoolkit);
        }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
