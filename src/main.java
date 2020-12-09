import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class main {

	static ArrayList<LocalDate> dates = new ArrayList<>();
	static ArrayList<Double> closeValue = new ArrayList<>();

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws MalformedURLException, JSONException, IOException, SQLException {
		System.out.println(
				"Welche Aktie möchten sie bekommen? \n" + "tsla ... Tesla\n" + "ADR ... American Depository Receipt.");
		String aktie = sc.next();
		APIService.getData(aktie, dates, closeValue);
		final Connection connection = DatabaseManager.getInstance().getDatabaseConnection(3306, "aktien");
		for (int i = 0; i < closeValue.size(); i++) {
			DatabaseManager.getInstance().insertIntoDatabase(connection, dates.get(i),closeValue.get(i),aktie);
		}
		sc.close();
	}
}