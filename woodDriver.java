public class woodDriver {

    public static void main(String[] args) {
        try {
            //wood test = new wood(2,4,8,"Cedar",3,false);
            wood noInput= new wood();
            //System.out.println(test);
            System.out.println(noInput);
        } catch (woodException e) {
            System.out.println("error to");
        }



    }
}
