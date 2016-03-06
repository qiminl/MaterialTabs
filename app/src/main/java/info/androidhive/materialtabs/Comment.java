package info.androidhive.materialtabs;

/**
 * Created by liuqi on 3/6/2016.
 */
public class Comment {

    private String COLUMN_ID = "_id";
    private String COLUMN_OPP_ID = "opportunity_id";
    private String COLUMN_USER_ID = "user_id";
    private String COLUMN_URL= "url";
    private String COLUMN_URI= "uri";
    private String COLUMN_COMMENT = "comment";

    public Comment(){
    }
    public Comment(String id, String opid,String userid,String url,String uri, String comment){
        COLUMN_ID = id;COLUMN_OPP_ID = opid;COLUMN_USER_ID = userid;
        COLUMN_URL = url; COLUMN_URI = uri; COLUMN_COMMENT = comment;
    }
    //todo change this.. too lazy to type right now.
    public String getCOLUMN_ID(){return COLUMN_ID;}
    public String getCOLUMN_OPP_ID(){return COLUMN_OPP_ID;}
    public String getCOLUMN_USER_ID(){return COLUMN_USER_ID;}
    public String getCOLUMN_URL(){return COLUMN_URL;}
    public String getCOLUMN_URI(){return COLUMN_URI;}
    public String getCOLUMN_COMMENT(){return COLUMN_COMMENT;}

    public void setCOLUMN_ID(String temp){ COLUMN_ID= temp;}
    public void setCOLUMN_OPP_ID(String temp){ COLUMN_OPP_ID= temp;}
    public void setCOLUMN_USER_ID(String temp){ COLUMN_USER_ID= temp;}
    public void setCOLUMN_URL(String temp){ COLUMN_URL= temp;}
    public void setCOLUMN_URI(String temp){ COLUMN_URI= temp;}
    public void setCOLUMN_COMMENT(String temp){ COLUMN_COMMENT= temp;}





}
