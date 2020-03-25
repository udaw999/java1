package ru.progwards.java1.lessons.sets;

import java.util.*;

public class ProductAnalytics {
    private List<Shop> shops;//список магазинов
    private List<Product> products;//список всех имеющихся в ассортименте товаров.

    public ProductAnalytics(List<Product> products, List<Shop> shops){
        this.shops = shops;
        this.products = products;
    }

    //товары из products, которые имеются во всех магазинах
    //товары из products, которые имеются во всех магазинах
    public Set<Product> existInAll(){
        HashSet products = new HashSet(this.products);//список всех имеющихся в ассортименте товаров.
        //добавляем из всех магазинов товары в одну колекцию
        for (Iterator<Shop> iterator = shops.iterator(); iterator.hasNext(); ) {
            Shop shopsProducts = iterator.next();
            //все товары из одного магазина
            products.retainAll( shopsProducts.getProducts());//пересечение множеств

        }

        return products;
    }

    //товары из products, которые имеются хотя бы в одном магазине
    public Set<Product> existAtListInOne(){
        HashSet aalShopProduct = new HashSet();//товары которые находятся во всех магазинах
        //добавляем из всех магазинов товары в одну колекцию
        for (Iterator<Shop> iterator = shops.iterator(); iterator.hasNext(); ) {
            Shop shopsProducts = iterator.next();
            //все товары из одного магазина
            aalShopProduct.addAll((Collection) shopsProducts.getProducts());
        }
        aalShopProduct.retainAll(products);//пересечение множеств
        return aalShopProduct;
    }
    //товары из products, которых нет ни в одном магазине
    public Set<Product> notExistInShops(){
        HashSet products = new HashSet(this.products);//список всех имеющихся в ассортименте товаров.
        products.removeAll(existAtListInOne());//удаляем пересечение множеств(все товары которые есть в магазинах)
        return products;
    }
    //товары из products, которые есть только в одном магазине
    public Set<Product> existOnlyInOne(){
        HashSet products = new HashSet(existAtListInOne());//товары из products, которые имеются хотя бы в одном магазине
        products.removeAll(existInAll());//удаляем все товар который встречается во всех магазинах

        return products;
    }

    public static void main(String[] args) {
        Product product = new Product("muka");
        Product product1 = new Product("xleb");
        Product product2 = new Product("bulka");
        Product product3 = new Product("pranik");

        Product product4 = new Product("ruchra");
        Product product5 = new Product("kniga");
        Product product6 = new Product("tetratka");
        Product product7 = new Product("lineika");

        Product product8 = new Product("voda");
        Product product9 = new Product("sok");
        Product product10 = new Product("vodka");
        Product product11 = new Product("pivo");

        Shop shop = new Shop(new LinkedList<>(List.of(product,product1,product2,product3,product5)));
        Shop shop2 = new Shop(new LinkedList<>(List.of(product4,product5,product6,product7,product,product2)));

        ProductAnalytics productAnalytics = new ProductAnalytics(
                new LinkedList<Product>(List.of(product,product1,product2,product3,product4,product5,product6,product7,
                        product8,product9,product10,product11)),
                new LinkedList<Shop>(List.of(shop,shop2))
        );

        //System.out.println(shop);
        System.out.println("асортимент");
        System.out.println(productAnalytics.products);
        System.out.println("весь товар во всех магазинах");
        System.out.println(productAnalytics.shops);
        System.out.println("товар который встречается во всех магазинах");
        System.out.println(productAnalytics.existInAll());
        System.out.println("встречается хотябы в одном из магазинов");
        System.out.println(productAnalytics.existAtListInOne());
        System.out.println("нет ни в одном магазине");
        System.out.println(productAnalytics.notExistInShops());
        System.out.println("которые есть только в одном магазине");
        System.out.println(productAnalytics.existOnlyInOne());
    }
}
