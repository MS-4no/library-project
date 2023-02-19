package la.bean;

import java.util.ArrayList;
import java.util.List;

import la.dao.DAOException;

public class CartBean {
    private List<ItemBean> items = new ArrayList<ItemBean>();

    public CartBean() {
    }

    public List<ItemBean> getItems() {
        return items;
        }

    public void addCart(ItemBean bean) throws DAOException {
        ItemBean item = null;
        for (ItemBean i : items) {
            if (i.getCode() == bean.getCode()) {
                item = i;
                break;
            }
        }
		
        if (item == null) {
            items.add(bean);
        } else {
        	
        }
        
        }

    public void deleteCart(int itemCode) {
        for (ItemBean item : items) {
            if (item.getCode() == itemCode) {
                items.remove(item);
                break;
            }
        }
    }


   
    
    
    public void deleteCartAll() {
        items.clear();
      
    }
}