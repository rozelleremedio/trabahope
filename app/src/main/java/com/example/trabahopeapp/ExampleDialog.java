package com.example.trabahopeapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.trabahopeapp.User.AvailableJobs;
import com.example.trabahopeapp.ViewAll.JobDetails;

public class ExampleDialog extends AppCompatDialogFragment {

    CheckBox chPart, chFull, chContract, chEntry, chExperienced, chAdvanced, chManager, chTopManager;
    String shPart, sFull, sContract, sEntry, sExperienced, sAdvanced, sManager, sTopManager;
    String jobtypes = "";
    String joblevel = "";
    private ExampleDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {

            throw new ClassCastException(context.toString() + "Must implement example dialog listner");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.filter_search_layout, null);
        chPart = (CheckBox) view.findViewById(R.id.checkPartTime);
        chFull = (CheckBox) view.findViewById(R.id.checkFullTime);
        chContract = (CheckBox) view.findViewById(R.id.checkContract);
        chEntry = (CheckBox)view.findViewById(R.id.checkEntryLvl);
        chExperienced = (CheckBox)view.findViewById(R.id.checkExperiencedLvl);
        chAdvanced = (CheckBox)view.findViewById(R.id.checkAdvancedLvl);
        chManager = (CheckBox)view.findViewById(R.id.checkManagerialLvl) ;
        chTopManager = (CheckBox)view.findViewById(R.id.checkTopManagementLvl);






        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                StringBuffer result = new StringBuffer();

                result.append("Job Type");
                if(chPart.isChecked()){
                    jobtypes = "2";
                    result.append(chPart.getText().toString());
                }
                if(chFull.isChecked()){
                    jobtypes = "1";
                    result.append(chFull.getText().toString());
                }
                if(chContract.isChecked()){
                    jobtypes = "3";
                    result.append(chContract.getText().toString());
                }
                if(chPart.isChecked() == true && chFull.isChecked() == true ){
                    jobtypes = "4";
                    result.append(chContract.getText().toString());
                }
                if(chFull.isChecked() == true && chContract.isChecked() == true ){
                    jobtypes = "5";
                    result.append(chContract.getText().toString());
                }
                if(chPart.isChecked() == true && chContract.isChecked() == true ){
                    jobtypes = "6";
                    result.append(chContract.getText().toString());
                }
                if(chEntry.isChecked()){
                    joblevel = "1";
                }
                if(chExperienced.isChecked()){
                    joblevel = "2";
                }
                if(chAdvanced.isChecked()){
                    joblevel = "3";
                }
                if(chManager.isChecked()){
                    joblevel = "4";
                }
                if(chTopManager.isChecked()){
                    joblevel = "5";
                }

                if(chEntry.isChecked() == true && chExperienced.isChecked() == true ){
                    joblevel = "6";
                    result.append(chContract.getText().toString());
                }
                if(chEntry.isChecked() == true && chAdvanced.isChecked() == true ){
                    joblevel = "7";
                    result.append(chContract.getText().toString());
                }
                if(chEntry.isChecked() == true && chManager.isChecked() == true ){
                    joblevel = "8";
                    result.append(chContract.getText().toString());
                }
                if(chEntry.isChecked() == true && chTopManager.isChecked() == true ){
                    joblevel = "9";
                    result.append(chContract.getText().toString());
                }
                if(chContract.isChecked() == true && chFull.isChecked() == true && chPart.isChecked() == true){
                    jobtypes = "10";
                }
                if(chContract.isChecked() == false && chFull.isChecked() == false && chPart.isChecked() == false){
                    jobtypes = "10";
                }
                if(chEntry.isChecked() == false && chExperienced.isChecked() == false && chAdvanced.isChecked() == false && chManager.isChecked() == false && chTopManager.isChecked() == false){
                    joblevel = "10";
                }
                if(chEntry.isChecked() == true && chExperienced.isChecked() == true && chAdvanced.isChecked() == true && chManager.isChecked() == true && chTopManager.isChecked() == true){
                    joblevel = "10";
                }





                Intent intent = new Intent(getActivity(),AvailableJobs.class);
                intent.putExtra("jobtype", jobtypes);
                intent.putExtra("joblevel", joblevel);
                startActivity(intent);



//                Toast.makeText(getActivity(), jobtypes, Toast.LENGTH_SHORT).show();

            }
        });



        return builder.create();

    }
    public interface ExampleDialogListener{

        void applyText(String passjobtype);



    }
}
