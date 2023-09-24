package demo;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private Integer price;

    private Book(String title, String author, Integer price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("");
        buffer.append("书名：《")
                .append(title)
                .append("》 ")
                .append("作者：")
                .append(author)
                .append(" ")
                .append("价格")
                .append(price.toString());
        return buffer.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Book oBook = (Book) obj;
            return this.title.equals(oBook.title) && this.author.equals(oBook.author)
                    && this.price.equals(oBook.price);
        }
    }

    @Override
    public int hashCode() {
        StringBuffer buffer = new StringBuffer("");
        buffer.append(title);
        buffer.append(author);
        buffer.append(price.toString());
        return buffer.toString().hashCode();

    }

    public static class BookBuilder {

        private String title;
        private String author;
        private Integer price;

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder title() {
            System.out.println("输入标题：");
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder author() {
            System.out.println("输入作者：");
            return this;
        }

        public BookBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public BookBuilder price( ) {
            System.out.println("输入价格：");
            return this;
        }

        public Book build() {
            return new Book(title, author, price);
        }
    }
}
