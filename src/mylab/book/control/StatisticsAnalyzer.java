package mylab.book.control;

import mylab.book.entity.*;
import java.text.DecimalFormat;
import java.util.*;

public class StatisticsAnalyzer {

    private String getPublicationType(Publication pub){

        if(pub instanceof Novel) return "소설";
        if(pub instanceof Magazine) return "잡지";
        if(pub instanceof ReferenceBook) return "참고서";

        return "기타";
    }

    public Map<String, Double> calculateAveragePriceByType(Publication[] pubs){

        Map<String,Integer> sum=new HashMap<>();
        Map<String,Integer> count=new HashMap<>();

        for(Publication p:pubs){

            String type=getPublicationType(p);

            sum.put(type,sum.getOrDefault(type,0)+p.getPrice());
            count.put(type,count.getOrDefault(type,0)+1);
        }

        Map<String,Double> result=new HashMap<>();

        for(String type:sum.keySet()){
            result.put(type,(double)sum.get(type)/count.get(type));
        }

        return result;
    }

    public Map<String,Double> calculatePublicationDistribution(Publication[] pubs){

        Map<String,Integer> count=new HashMap<>();

        for(Publication p:pubs){

            String type=getPublicationType(p);

            count.put(type,count.getOrDefault(type,0)+1);
        }

        Map<String,Double> result=new HashMap<>();

        for(String type:count.keySet()){
            result.put(type,(double)count.get(type)/pubs.length*100);
        }

        return result;
    }

    public double calculatePublicationRatioByYear(Publication[] pubs,String year){

        int count=0;

        for(Publication p:pubs){

            if(p.getPublishDate().startsWith(year)) count++;
        }

        return (double)count/pubs.length*100;
    }

    public void printStatistics(Publication[] pubs){

        DecimalFormat df=new DecimalFormat("#,###.##");

        System.out.println("\n===== 출판물 통계 분석 =====");

        System.out.println("1. 타입별 평균 가격:");

        Map<String,Double> avg=calculateAveragePriceByType(pubs);

        for(String type:avg.keySet()){
            System.out.println("   - "+type+": "+df.format(avg.get(type))+"원");
        }

        System.out.println("\n2. 출판물 유형 분포:");

        Map<String,Double> dist=calculatePublicationDistribution(pubs);

        for(String type:dist.keySet()){
            System.out.println("   - "+type+": "+df.format(dist.get(type))+"%");
        }

        double ratio=calculatePublicationRatioByYear(pubs,"2007");

        System.out.println("\n3. 2007년에 출판된 출판물 비율: "+df.format(ratio)+"%");
    }
}