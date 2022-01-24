public class StringConvertTester {
    public static void main(String[] args) throws Exception {
        String dummy = "åŸƒå…‹æ£®ç¾Žå­¦èŒ‚å…¨å±ž20051C";
        String result = new String(dummy.getBytes("ISO-8859-1"),"utf-8");
        System.out.println(result);
    }
}
