package zqh.commons.excelModel;

public class AlumnusExcel {

    public final static String ID = "编号";
    public final static String NAME = "姓名";
    public final static String ID_CARD = "身份证号";
    public final static String STUDENT_NO = "学号";
    public final static String NATION = "民族";
    public final static String TEL = "电话";
    public final static String COLLEGE = "学院";
    public final static String MAJOR = "专业";
    public final static String BIRTHDAY = "生日";
    public final static String ENROL_DATE = "入学日期";
    public final static String GRADUATION_DATE = "毕业日期";
    public final static String NATIVE_PLACE = "籍贯";
    public final static String POLITICAL_STATUS = "政治面貌";
    public final static String STATUS = "状态";
    public final static String SPECIAL = "特殊校友";

    public final static String getStatus(int code) {
        switch (code){
            case 0 : return "毕业";
            case 1 : return "在学";
            case 2 : return "休学";
            case 3 : return "退学";
            case 4 : return "结业";
            default: return "";
        }
    }

    public final static int getStatusCode(String str) {
        switch (str){
            case "毕业" : return 0;
            case "在学" : return 1;
            case "休学" : return 2;
            case "退学" : return 3;
            case "结业" : return 4;
            default: return -1;
        }
    }

    public final static String[] getImportColumns(){
        return new String[]{NAME,ID_CARD,STUDENT_NO,TEL,COLLEGE,MAJOR,BIRTHDAY,ENROL_DATE,GRADUATION_DATE,
                NATIVE_PLACE,POLITICAL_STATUS,STATUS};
    }
}
