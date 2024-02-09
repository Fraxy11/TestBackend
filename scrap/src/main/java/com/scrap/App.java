package com.scrap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
// import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class App {
        /**
         * @param args
         * @throws Exception
         */
        public static void main(String[] args) throws Exception {
                System.setProperty("webdriver.chrome.driver",
                                "D:\\Belajar Java\\Java\\Mvn.java\\browser\\chromedriver-win64\\chromedriver.exe");
                WebDriver driver = new ChromeDriver();

                String tautan = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";

                driver.get(tautan);
                driver.manage().window();
                driver.manage().deleteAllCookies();
                // driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
                // driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSession();

                driver.get(tautan);
                List<WebElement> links = driver.findElements(By.tagName("a"));

                for (int i = 364; i < links.size(); i += 2) {
                        try {

                                WebElement link = links.get(i);
                                String href = link.getAttribute("href");
                                System.out.println("melakukan klik ke " + href);

                                // Klik link dengan membuka tab baru menggunakan javascript
                                ((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');",
                                                href);

                                // Tunggu sejenak untuk memastikan tab baru telah dibuka
                                try {
                                        Thread.sleep(5000); // Atur waktu tunggu
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }

                                // Switch ke tab baru
                                String originalWindowHandle = driver.getWindowHandle();
                                for (String windowHandle : driver.getWindowHandles()) {
                                        if (!windowHandle.equals(originalWindowHandle)) {
                                                driver.switchTo().window(windowHandle);
                                                break;
                                        }
                                }
                        
                                        // Lakukan operasi scraping pada elemen yang ditemukan

                                String Title = driver.findElement(By.className("hero__primary-text")).getText();

                                Float Rating = Float.parseFloat(
                                                driver.findElement(By.cssSelector(".sc-bde20123-2.cdQqzc")).getText()
                                                                .split("/")[0]);

                                String Year = driver.findElement(By.cssSelector(
                                                "section[cel_widget_id=\"StaticFeature_Details\"] li[data-testid=\"title-details-releasedate\"] .ipc-inline-list.ipc-inline-list--show-dividers.ipc-inline-list--inline.ipc-metadata-list-item__list-content.base"))
                                                .getText().split(" ")[2];

                                String Month = driver.findElement(By.cssSelector(
                                                "section[cel_widget_id=\"StaticFeature_Details\"] li[data-testid=\"title-details-releasedate\"] .ipc-inline-list.ipc-inline-list--show-dividers.ipc-inline-list--inline.ipc-metadata-list-item__list-content.base"))
                                                .getText().split(" ")[0];

                                String Certificate = driver.findElement(By.xpath(
                                                ".//*[@id=\"__next\"]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[2]"))
                                                .getText();
                                String Runtime = "";
                                try{
                                Runtime = driver.findElement(By.xpath(
                                                ".//*[@id=\"__next\"]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[3]"))
                                                .getText();
                                        }catch(Exception e){}
                                String Director = driver
                                                .findElement(By.cssSelector(
                                                                ".ipc-metadata-list-item__content-container"))
                                                .getText();
                                List<WebElement> Stars = driver.findElements(By.cssSelector(
                                                "div[data-testid=\"shoveler-items-container\"] .sc-bfec09a1-5.hNfYaW a[data-testid=\"title-cast-item__actor\"]"));

                                List<String> nameStars = Stars.stream().map(e -> e.getText())
                                                .collect(Collectors.toList());

                                String Genre = driver
                                                .findElement(By.cssSelector(
                                                                "div[class=\"ipc-chip-list__scroller\"] a span"))
                                                .getText();
                        
                                String FilmingLocation = "";  
                                try{ 
                                FilmingLocation = driver.findElement(By.cssSelector(
                                                "section[cel_widget_id=\"StaticFeature_Details\"] li[data-testid=\"title-details-filminglocations\"] .ipc-metadata-list-item__content-container"))
                                                .getText();
                                        }catch(Exception e){}
                                Integer Budget = 0;
                                try{
                                 Budget = Integer.parseInt(driver
                                                .findElement(By.cssSelector(
                                                                "div[data-testid=\"title-boxoffice-section\"] ul li:first-child div"))
                                                .getText().split(" ")[0].replaceAll("[^0-9]", ""));
                                        }catch(Exception e){}

                                Integer Income = 0;  
                                try{
                                Income = Integer.parseInt(driver
                                                .findElement(
                                                                By.cssSelector("div[data-testid=\"title-boxoffice-section\"] ul li:nth-child(2) div"))
                                                .getText().split(" ")[0].replaceAll("[^0-9]", ""));
                                        }catch(Exception e){}

                                String CountryofOrigin = driver.findElement(By.cssSelector(
                                                "section[cel_widget_id=\"StaticFeature_Details\"] li[data-testid=\"title-details-origin\"] a")).getText();
                        
                        
                        
                        String urls = "jdbc:mysql://localhost:3306/moavies";
                        String username = "root";
                        String password = "1234";            
                                
                                // try (Connection connection = DriverManager.getConnection(
                                //                 "jdbc:mysql://localhost:3306/moavies", "root", "1234")) {
                                
                                        try {
                                                // Membuat koneksi
                                                Connection connection = DriverManager.getConnection(urls, username, password);
                                    

                                                                // Posisikan query insert di sini
                                        String insertQuery = "INSERT INTO imdb (Title, Rating, Year, Month, Certificate, Runtime, Director, Stars, Genre, FilmingLocation, Budget, Income, CountryOfOrigin) "
                                                        +
                                                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                                        try (PreparedStatement preparedStatement = connection
                                                        .prepareStatement(insertQuery)) {
                                                // Mengganti placeholder dengan nilai aktual
                                                preparedStatement.setString(1, Title);
                                                preparedStatement.setFloat(2, Rating);
                                                preparedStatement.setString(3, Year);
                                                preparedStatement.setString(4, Month);
                                                preparedStatement.setString(5, Certificate);
                                                preparedStatement.setString(6, Runtime);
                                                preparedStatement.setString(7, Director);
                                                preparedStatement.setString(8, String.join(", ", nameStars));
                                                preparedStatement.setString(9, Genre);
                                                preparedStatement.setString(10, FilmingLocation);
                                                preparedStatement.setInt(11, Budget);
                                                preparedStatement.setInt(12, Income);
                                                preparedStatement.setString(13, CountryofOrigin);

                                                // Eksekusi query insert
                                                preparedStatement.executeUpdate();

                                                System.out.println("Data berhasil ditambahkan ke database.");
                                        }
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                        System.err.println(
                                                        "Gagal terkoneksi dengan database atau gagal menambahkan data.");
                                }
                        
                                // System.out.println(Title);
                                // System.out.println(Rating);
                                // System.out.println(Year);
                                // System.out.println(Month);
                                // System.out.println(Certificate);
                                // System.out.println(Runtime);
                                // System.out.println(Director);
                                // System.out.println(nameStars);
                                // System.out.println(Genre);
                                // System.out.println(FilmingLocation);
                                // System.out.println(Budget);
                                // System.out.println(Income);
                                // System.out.println(CountryofOrigin);
                        // }
                                Thread.sleep(Duration.ofSeconds(10));
                                // Tutup tab baru
                                driver.close();

                                // Switch kembali ke tab awal
                                driver.switchTo().window(originalWindowHandle);

                        } catch (Exception e) {
                                // System.out.println(e.getMessage());
                                throw e;

                        }
                }
                driver.quit();
                }

}

