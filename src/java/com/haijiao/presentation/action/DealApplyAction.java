/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

public class DealApplyAction extends RequestSessionAction {
    
    @Override
    public String execute(){
        if("stop".equals((String)this.getOutRequest("button"))){
            stop();
        } else if("accept".equals((String)this.getOutRequest("button"))){
            accept();
        } else if("decline".equals((String)this.getOutRequest("button"))){
            decline();
        } else {
            //do nothing;
        }
        return SUCCESS;
    }
    
    public void stop(){
        
    }
    
    public void accept(){
        
    }
    
    public void decline(){
        
    }
    
}
