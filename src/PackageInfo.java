public class PackageInfo {

    String packageName;
    int ca;
    int ce;

    public double getInstability(){
        return ce/(ca+ce);
    }

    int na;
    int nc;

    public double getAbstactness(){
        return na/nc;
    }

}
