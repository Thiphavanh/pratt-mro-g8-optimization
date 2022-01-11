package com.utas.solumina.paar.application;

public interface AssembleText
{    
    public String permSghtValidMode(String requestId,
                                    String validToolMode);

    public String permSghtAssembleText(String requestId,
                                       String validToolMode);
    
    //UTAS-478
    public String permApprovalValidMode(String requestId,
            					        String validToolMode);
    
    //UTAS-478
    public String permApprovalAssembleText(String requestId,
            							   String validToolMode);
    
    public String widPermSghtValidMode(String requestId,
			   						   String validToolMode);
    
    public String widPermSghtAssembleText(String requestId,
			  							  String validToolMode);

    //UTAS-496
    public String permOrderSecGrpApprovalValidMode(String requestId,
    											   String validToolMode);
    
    //UTAS-496
    public String permOrderSecGrpApprovalAssembleText(String requestId,
    												  String validToolMode);
}
