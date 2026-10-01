package Btth;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDAO dao = new ProductDAO();
        int choice = 0;
        do {
            System.out.println();
            System.out.println(" PRODUCT MANAGEMENT");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Sắp xếp theo giá tăng dần");
            System.out.println("7. Thống kê theo danh mục");
            System.out.println("8. Thoát");
            System.out.print("Chọn chức năng: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Phải nhập số!");
                continue;
            }
            switch (choice) {
                case 1: {
                    List<Product> list = dao.getAllProducts();
                    for (Product p : list) {
                        System.out.println(p);
                    }
                    break;
                }
                case 2: {
                    try {
                        System.out.print("Nhập tên sản phẩm: ");
                        String name = sc.nextLine();
                        if (name.trim().isEmpty()) {
                            throw new Exception("Tên không được để trống!");
                        }
                        System.out.print("Nhập giá: ");
                        double price = Double.parseDouble(sc.nextLine());
                        if (price <= 0) {
                            throw new Exception("Giá phải lớn hơn 0!");
                        }
                        System.out.print("Nhập tiêu đề: ");
                        String title = sc.nextLine();
                        if (title.trim().isEmpty()) {
                            throw new Exception("Tiêu đề không được trống!");
                        }
                        System.out.print("Nhập ngày yyyy-MM-dd: ");
                        LocalDate created = LocalDate.parse(sc.nextLine());
                        System.out.print("Nhập danh mục: ");
                        String catalog = sc.nextLine();
                        if (catalog.trim().isEmpty()) {
                            throw new Exception("Danh mục không được trống!");
                        }
                        System.out.print("Trạng thái true/false: ");
                        boolean status = Boolean.parseBoolean(sc.nextLine());
                        Product product = new Product(name, price, title, created, catalog, status);
                        if (dao.addProduct(product)) {
                            System.out.println("Thêm sản phẩm thành công!");
                        }
                    } catch (Exception e) {
                        System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
                    }
                    break;
                }
                case 3: {
                    try {
                        System.out.print("Nhập ID cần cập nhật: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập tên mới: ");
                        String name = sc.nextLine();
                        System.out.print("Nhập giá mới: ");
                        double price = Double.parseDouble(sc.nextLine());
                        if (price <= 0) {
                            throw new Exception("Giá phải > 0");
                        }
                        System.out.print("Nhập tiêu đề mới: ");
                        String title = sc.nextLine();
                        System.out.print("Nhập ngày yyyy-MM-dd: ");
                        LocalDate date = LocalDate.parse(sc.nextLine());
                        System.out.print("Nhập danh mục: ");
                        String catalog = sc.nextLine();
                        System.out.print("Trạng thái true/false: ");
                        boolean status = Boolean.parseBoolean(sc.nextLine());
                        Product product = new Product(id, name, price, title, date, catalog, status);
                        if (dao.updateProduct(product)) {
                            System.out.println("Cập nhật thành công!");
                        }
                    } catch (Exception e) {
                        System.out.println("Dữ liệu không hợp lệ!");
                    }
                    break;
                }
                case 4: {
                    System.out.print("Nhập ID cần xóa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    if (dao.deleteProduct(id)) {
                        System.out.println("Xóa thành công!");
                    }
                    break;
                }
                case 5: {
                    System.out.print("Nhập tên cần tìm: ");
                    String name = sc.nextLine();
                    List<Product> list = dao.searchProduct(name);
                    if (list.isEmpty()) {
                        System.out.println("Không tìm thấy sản phẩm!");
                    } else {
                        for (Product p : list) {System.out.println(p);
                        }
                    }
                    break;
                }
                case 6: {
                    List<Product> list = dao.getAllProducts();
                    list.sort(Comparator.comparingDouble(Product::getPrice));
                    for (Product p : list) {
                        System.out.println(p);
                    }
                    break;
                }

                case 7: {
                    dao.statistic();
                    break;
                }
                case 8: {
                    System.out.println("Thoát chương trình!");
                    break;
                }
                default: {
                    System.out.println("Lựa chọn không hợp lệ!");
                }
            }
        } while (choice != 8);
    }
}
