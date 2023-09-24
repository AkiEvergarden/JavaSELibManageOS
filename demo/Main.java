package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static Map<Integer, Book> bookMap = new TreeMap<>();
    private static Integer cnt = new Integer(0);

    public static void main(String[] args) {
        load();
        Scanner scanner = new Scanner(System.in);
        boolean menu = true;
        while (menu) {
            System.out.println("=========欢迎使用图书管理系统==========");

            System.out.println("1、录入图书信息");

            System.out.println("2、查询图书信息");

            System.out.println("3、修改图书信息");

            System.out.println("4、删除图书信息");

            System.out.println("5、退出管理系统");

            System.out.println("=====================================");
            int i = Integer.parseInt(scanner.nextLine());
            switch (i) {
                case 1: {
                    insert(scanner);
                    save();
                    break;
                }
                case 2: {
                    show();
                    break;
                }
                case 3: {
                    modify(scanner);
                    save();
                    break;
                }
                case 4: {
                    delete(scanner);
                    save();
                    break;
                }
                case 5: {
                    menu = false;
                    System.out.println("图书信息保存中。。。");
                    save();
                    System.out.println("再见");
                    break;
                }
                default: {
                    System.out.println("输入了非法字符");
                    break;
                }
            }

        }
    }

    private static void insert(Scanner scanner) {
        Book book = new Book.BookBuilder()
                .title()
                .title(scanner.nextLine())
                .author()
                .author(scanner.nextLine())
                .price()
                .price(Integer.parseInt(scanner.nextLine())).build();

        bookMap.put(++cnt, book);
    }

    private static void modify(Scanner scanner) {
        System.out.println("输入需要修改书籍的ID");
        Integer i = Integer.parseInt(scanner.nextLine());
        
        if(!bookMap.containsKey(i)){
            System.out.println("不存在该书");
            return;
        }
        Book book = new Book.BookBuilder()
                .title()
                .title(scanner.nextLine())
                .author()
                .author(scanner.nextLine())
                .price()
                .price(Integer.parseInt(scanner.nextLine())).build();

        bookMap.put(i, book);
        System.out.println("更新成功");
    }

    private static void show() {
        bookMap.forEach((key, value) -> {
            System.out.println("ID: " + key.toString() + "、" + value.toString());
        });
    }

    private static void delete(Scanner scanner) {
        System.out.println("输入删除要删除书籍的ID，输入0退出");
        while (true) {
            Integer i = Integer.parseInt(scanner.nextLine()), j;
            if (i == 0) {
                break;
            }
            System.out.println("请再次输入：");
            j = Integer.parseInt(scanner.nextLine());
            if (j == 0) {
                break;
            } else if (j == i) {
                if (bookMap.remove(i) == null) {
                    System.out.println("删除失败，请重新输入");
                } else {
                    System.out.println("删除成功");
                    break;
                }
            } else {
                System.out.println("前后输入不一致");
            }
        }

    }

    private static void load() {
        File file = new File("data");
        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                bookMap = (Map<Integer, Book>) stream.readObject();
                bookMap.forEach((k, value) -> {
                    cnt = k;
                });
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

    }

    private static void save() {
        File file = new File("data");
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(bookMap);
            stream.flush();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}