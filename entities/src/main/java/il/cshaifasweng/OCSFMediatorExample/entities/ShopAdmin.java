package il.cshaifasweng.OCSFMediatorExample.entities;

public class ShopAdmin extends User{
    private final int shop_id;

    public ShopAdmin(int user_id, String first_name, String last_name, String user_email, int shop_id){
        super(user_id, first_name, last_name, user_email);
        this.shop_id = shop_id;
    }

    public int getShop_id(){
        return shop_id;
    }

}
