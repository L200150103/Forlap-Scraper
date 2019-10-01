package company.wkdk.id.retrofit;

public class User {

    private String nim;
    private String link;
    private String prodi;


    public User(String nim, String prodi) {
        this.nim = nim;
        this.prodi = prodi;
    }

    public String getName() {
        return nim;
    }

    public String getJob() {
        return link;
    }


}
