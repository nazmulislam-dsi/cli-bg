package org.ni.rpg;

import org.ni.rpg.services.GameEngineService;


/**
 * Created by nazmul on 9/28/2018.
 */
public class Main {
    public static void main(String [] args) {
        try {
            GameEngineService.getInstance().init();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Hoping, with the next update these errors will be fixed. Sorry for inconveniences. Thanks");
            System.exit(1);
        }
    }
}
