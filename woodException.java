public class woodException extends Exception {
//todo fix code so that i can throw new woodException instead of throws Exception in other method headers

    public woodException(String input) {
       super("-"+input+"-");
    }

    public woodException(){
        super("-Exception is thrown-");
    }
}
