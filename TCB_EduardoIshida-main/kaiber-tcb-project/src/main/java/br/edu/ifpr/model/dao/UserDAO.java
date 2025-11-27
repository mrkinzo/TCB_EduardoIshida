public static void cadastrarUser(User user) throws SQLException { 
    String sqlUser = "INSERT INTO user(nome, instituicao, cargo) VALUES(?,?,?)"; 

    try{ 
        PreparedStatement psUser = con.prepareStatement(sqlUser);
        psUser.setString(1, user.getName());
        psUser.setString(2, user.getInstitution());
        psUser.setString(3, user.getRole());
        psUser.executeUpdate();
        System.out.println("----BEM VINDO----");
    } catch (SQLException e) { 
        e.printStackTrace();
    } finally {
        if (conn!= null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
}