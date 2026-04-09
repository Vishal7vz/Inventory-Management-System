package inventory.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

/**
 * Demo product lists per category (no database). Used by sales and purchase
 * forms.
 */
public final class ProductCatalog {

    public static final String PLACEHOLDER = "— Select —";

    private static final Map<String, String[]> BY_CATEGORY = new LinkedHashMap<>();
    static List<String> categoryItemsList = new ArrayList<>();
    static List<String> categoriesList = new ArrayList<>();
    static String[] categoryItems;
    static String[] categories;

    public static void refreshCategoryCombo() {
        try {
            Connection conn = DBConnection.getConnection();
            String fetchAllProductCategory = "SELECT CATEGORY FROM PRODUCT GROUP BY CATEGORY;";
            String fetchEachCategoryProduct = "SELECT PRODUCT_ID FROM PRODUCT WHERE CATEGORY = ?";
            PreparedStatement psStmt = conn.prepareStatement(fetchAllProductCategory);
            PreparedStatement psStmt1 = conn.prepareStatement(fetchEachCategoryProduct);

            ResultSet res = psStmt.executeQuery();
            ResultSet res1;

            categoriesList.add(PLACEHOLDER);
            while (res.next()) {
                categoriesList.add(res.getString("CATEGORY"));
                psStmt1.setString(1, res.getString("CATEGORY"));
                res1 = psStmt1.executeQuery();
                while (res1.next()) {
                    categoryItemsList.add(res1.getString("PRODUCT_ID"));
                }

                categoryItems = categoryItemsList.toArray(new String[0]);

                BY_CATEGORY.put(res.getString("CATEGORY"), categoryItems);
                categoryItemsList.clear();
            }

            categories = categoriesList.toArray(new String[0]);
            categoriesList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ProductCatalog() {
    }

    /** Values for a category dropdown (placeholder + Hardware, Shoes, Grocery). */
    public static String[] categoryComboItems() {
        refreshCategoryCombo();
        return categories;
    }

    /**
     * Refill product dropdown when category changes; always starts with
     * placeholder.
     */
    public static void refreshProductCombo(JComboBox<String> productCombo, Object selectedCategory) {
        productCombo.removeAllItems();
        productCombo.addItem(PLACEHOLDER);
        if (selectedCategory == null || isPlaceholder(String.valueOf(selectedCategory))) {
            return;
        }
        String cat = String.valueOf(selectedCategory);
        String[] products = BY_CATEGORY.get(cat);
        if (products != null) {
            for (String p : products) {
                productCombo.addItem(p);
            }
        }
    }

    public static boolean isPlaceholder(String s) {
        return s == null || s.isEmpty() || PLACEHOLDER.equals(s);
    }
}
