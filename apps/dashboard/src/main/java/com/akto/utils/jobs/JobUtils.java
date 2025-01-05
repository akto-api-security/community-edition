package com.akto.utils.jobs;

import com.akto.dto.ApiCollection;
import com.akto.util.DashboardMode;

public class JobUtils {

    public static boolean getRunJobFunctions() {
        try {
            return Boolean.parseBoolean(System.getenv().getOrDefault("AKTO_RUN_JOB", "false"));
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean getRunJobFunctionsAnyway() {
        try {
            return DashboardMode.isOnPremDeployment() || !DashboardMode.isSaasDeployment();
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean hasHostModified(String hostNameFromParam, ApiCollection collection){
        if(collection.getHostName() == null){
            return true;
        }

        return collection.getHostName().equals(hostNameFromParam);
    }
}