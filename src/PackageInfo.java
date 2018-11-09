public class PackageInfo {

    String packageName;
    
    int ca;
    int ce;
    
    int na;
    int nc;


    public PackageInfo(String packageName, int ca,int ce,int na, int nc){
    	this.ca = ca;
    	this.ce = ce;
    	this.na = na;
    	this.nc = nc;
    }
    
    public double getInstability(){
        return ce/(ca+ce);
    }

    public double getAbstactness(){
        return na/nc;
    }

}
