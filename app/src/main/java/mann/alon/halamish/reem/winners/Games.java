package mann.alon.halamish.reem.winners;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Mr Bond on 27/05/2016.
 */
public class Games {
    String nameA;
    String nameB;
    ArrayList<Integer> winsA;
    ArrayList<Integer> winsB;
    double meanA;
    double meanB;
    double SEM_A;
    double SEM_B;

    public Games(String nameA, String nameB, ArrayList<Integer> winsA, ArrayList<Integer> winsB) {
        this.nameA = nameA;
        this.nameB = nameB;
        this.winsA = winsA;
        this.winsB = winsB;


        // calculate SEM and mean
        double sum=0;
        for (Integer i:winsA){sum=sum+i;}
        meanA=sum/winsA.size();

        sum=0;
        for (Integer i:winsB){sum=sum+i;}
        meanB=sum/winsB.size();

        double sum_squers=0;
        for (Integer i:winsA){
            double distance = (i - meanA) * (i-meanA);
            sum_squers += distance;
        }
        double std = sum_squers / (winsA.size()-1);
        SEM_A=std/ Math.sqrt(winsA.size());

        sum_squers=0;
        for (Integer i:winsA){
            double distance = (i - meanB) * (i-meanB);
            sum_squers += distance;
        }
        std = sum_squers / (winsB.size()-1);
        SEM_B=std/ Math.sqrt(winsB.size());

    }


    public String getNameA() {
        return nameA;
    }

    public String getNameB() {
        return nameB;
    }

    public double getMeanA() {
        return meanA;
    }

    public double getMeanB() {
        return meanB;
    }

    public double getSEM_A() {
        return SEM_A;
    }

    public double getSEM_B() {
        return SEM_B;
    }
}