public class woodException extends Exception {

    public woodException(String input) throws Exception{
        System.out.println("-"+input+"-");
        //throw new Exception();
    }

    public woodException() throws Exception{
        System.out.println("Exception as thrown ");
        //throw new Exception();
    }
}
