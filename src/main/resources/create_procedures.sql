CREATE ALIAS IF NOT EXISTS CALCULATE_SUM AS $$
import java.sql.*;

public static int calculateSum(int num1, int num2) throws SQLException {
    return num1 + num2;
}
$$;
