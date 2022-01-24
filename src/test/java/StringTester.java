public class StringTester {
    public static void main(String[] args) throws Exception {
        String tester = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<ExchangeDataPackage>\n" +
                "\t<RequestTime>2020-06-01 10:10:10</RequestTime>\n" +
                "\t<UserID>FFrobot</UserID>\n" +
                "\t<Password>db30fed4b3431b39b542ba48df9ab642</Password>\n" +
                "\t<RequestParams/>\n" +
                "</ExchangeDataPackage>";
        System.out.println(tester.isEmpty());
    }
}
