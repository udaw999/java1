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
        HashSet productsAll = new HashSet(existAtListInOne());//встречается хотябы в одном из магазинов
        HashSet productsEnd = new HashSet();//колекция первого магазина чтоб сравнить в конце с последним
        HashSet products = new HashSet();//промежуточная для сравнения одного магазина с другим(товара)
        //products.removeAll(existInAll());//удаляем все товар который встречается во всех магазинах


        int i = 0;
        for (ListIterator<Shop> listIteratorShops = shops.listIterator(); listIteratorShops.hasNext(); ) {
            Shop shopsProducts = listIteratorShops.next();//все товары из одного магазина
//SetOperations.symDifference()
           if(i==0){
               products.addAll((Collection) shopsProducts.getProducts());//добавили сперва кол. магаз 1
               productsEnd.addAll((Collection) shopsProducts.getProducts());//добавили сперва кол. магаз 1
           } else {
               products.retainAll((Collection) shopsProducts.getProducts());//на втором круге сравнили кол 1 с кол 2
               //и получили ттовары которые одинаковы в обеих магазинах
               productsAll.removeAll(products);//Чудалить одинаковые товары в productsAll колекцию

               products.clear();//очищаем колекцию products
               products.addAll((Collection) shopsProducts.getProducts());//добавили эту колекцию в чистый products

           }
            i++;

        }
        products.retainAll(productsEnd);//сравниваем первую с последней
        productsAll.removeAll(products);//удалить одинаковые товары в productsAll колекцию
        return productsAll;
    }

    public static void main(String[] args) {
        Product product = new Product("art-0");
        Product product1 = new Product("art-1");
        Product product2 = new Product("art-2");
        Product product3 = new Product("art-3");

        Product product4 = new Product("art-4");
        Product product5 = new Product("art-5");
        Product product6 = new Product("art-6");
        Product product7 = new Product("art-7");

        Product product8 = new Product("art-8");
        Product product9 = new Product("art-9");
        Product product10 = new Product("art-10");
        Product product11 = new Product("art-11");

//        Магазин 1, товары: art-3,art-6,art-7,art-8,art-10
//        Магазин 2, товары: art-1,art-2,art-7,art-8
//        Магазин 3, товары: art-1,art-3,art-6,art-8,art-10

        Shop shop = new Shop(new LinkedList<>(List.of(product3,product6,product7,product8,product10)));
        Shop shop2 = new Shop(new LinkedList<>(List.of(product1,product2,product7,product8)));
        Shop shop3 = new Shop(new LinkedList<>(List.of(product1,product3,product6,product8,product10)));

        ProductAnalytics productAnalytics = new ProductAnalytics(
                new LinkedList<Product>(List.of(product,product1,product2,product3,product4,product5,product6,product7,
                        product8,product9,product10,product11)),
                new LinkedList<Shop>(List.of(shop,shop2,shop3))
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
