
package business;

/**
 *
 * @author Rafael
 */
public class AssetSL extends Asset
{
    
    private double[] begbal, endbal;
    private double straightDep;
    
    public AssetSL()
    {
        super();        
    }   
    
    public AssetSL(String name, double cost, double salvage, int life)
    {
        super(name, cost, salvage, life);
        if (!super.getErrorMsg().isEmpty()) {
           calcDep(); 
        }        
    }        
    
    private void calcDep()
    {
        try {
            begbal = new double[super.getLife()];
            endbal = new double[super.getLife()];
        
            this.straightDep = (super.getCost() - super.getSalvage()) / super.getLife();
            begbal[0] = super.getCost();
            for (int i = 0; i < super.getLife(); i++)
            {
                if (i > 0) {
                    begbal[i] = endbal[i-1];                
                }
                endbal[i] = begbal[i] - this.straightDep;
            }
            super.setBuilt(true);
        } catch (Exception e){
            super.setBuilt(false);
        }    
        
    }  
    
    @Override
    public double getAnnDep(int yr)
    {
        //returns SL DEP value (equal in all yrs)
        if (!super.getBuilt())
        {
            calcDep();
        }  
        if (!super.getErrorMsg().isEmpty())
        {
                return -1;
        }    
        if (yr < 0 || yr > super.getLife())
        {
                return -1;
        }   
        return this.straightDep;
      
        
    }
    @Override
    public double getBegBal(int i) {
        if (!super.getBuilt())
        {
            calcDep();
        }  
        if (!super.getErrorMsg().isEmpty())
        {
                return -1;
        }    
        if (i < 0 || i > super.getLife())
        {
                return -1;
        } 
        return this.begbal[i];        
    }
    @Override
    public double getEndBal(int i) {
        if (!super.getBuilt())
        {
            calcDep();
        }  
        if (!super.getErrorMsg().isEmpty())
        {
                return -1;
        }    
        if (i < 0 || i > super.getLife())
        {
                return -1;
        } 
        return this.endbal[i];
    }
    
}
