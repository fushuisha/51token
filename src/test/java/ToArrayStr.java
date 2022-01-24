public class ToArrayStr {
    private static final String arg = "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200729-092153-817170.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-140645-787861.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-155528-297733.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200729-092758-852294.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-111548-116259.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-163422-765639.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-113728-282431.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-140926-879276.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-105826-633694.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-111645-521086.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-165531-536383.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-105851-062431.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-133816-448190.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-091240-300078.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-140927-124764.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-105511-720510.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-084819-480431.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-135842-768382.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-103345-061993.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-161732-634655.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-162611-949444.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-162611-769158.jpg\n" +
            "c:\\Users\\fushu\\Downloads\\mingri\\org\\QQ-FaHuo-20200728-154744-825435.jpg";

    public static void main(String[] args) throws Exception {
        String[] argArray = arg.split("\n");
        for (String argItem : argArray) {
            String argResult = "\"" + argItem.replace("\\", "/") + "\",";
            System.out.println(argResult);
        }
    }
}
