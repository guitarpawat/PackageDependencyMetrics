import stat.PackageStat;
import stat.PackageUsage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<String, Integer> usage;
    
    static List<PackageInfo> listOfInfo = new ArrayList<PackageInfo>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        PackageStat stat = new PackageStat(path);
        PackageUsage u = new PackageUsage(stat);
        usage = u.getPackages();
        walkFrom(stat);
        for(PackageInfo x : listOfInfo) {
        	System.out.println("name: "+x.packageName);
        	System.out.println("A: "+x.getAbstactness());
        	System.out.println("I: "+x.getInstability());
        	System.out.println("D': "+x.getDdass());
        }
    }

    public static void print(PackageStat root) {
        if(!root.getPackageName().isEmpty()) {
            printStat(root);
        }
        for(PackageStat ps : root.getSubPackages()) {
            print(ps);
        }
    }

    public static void printStat(PackageStat ps) {
        System.out.println("Package name: "+ps.getPackageName());
        System.out.println("No. of total class: "+ps.getClasses().size());
        System.out.println("No. of abstract class: "+ps.getAbstracts());
        System.out.println("No. of imports: "+ps.getImports());
        System.out.println("No. of class uses: "+usage.getOrDefault(ps.getPackageName(), 0));
        System.out.println("===========================================");
    }
    
    
    public static void walkFrom(PackageStat root) {
        if(!root.getPackageName().isEmpty()) {
        	PackageInfo x = createpckgInfo(root);
//        	System.out.println(x.toString());
        	listOfInfo.add(x);
        }
        for(PackageStat ps : root.getSubPackages()) {
        	walkFrom(ps);
        }
    }

    public static PackageInfo createpckgInfo(PackageStat ps) {
    	return new PackageInfo(ps.getPackageName(), usage.getOrDefault(ps.getPackageName(), 0) , ps.getImports(), ps.getAbstracts() , ps.getClasses().size());
    }
}
