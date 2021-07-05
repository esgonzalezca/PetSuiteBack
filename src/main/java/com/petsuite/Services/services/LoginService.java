package com.petsuite.Services.services;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.services.interfaces.ILogin;
import com.petsuite.controller.TokenController;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class LoginService implements ILogin {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    TokenController tokenController;

    public static String encryptThisString(String input)
	{
		try {
			// getInstance() method is called with algorithm SHA-512
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
    
    @Override
    public Object clientLogin(InfoUser_Dto user){
        
        
        String sqlA = "SELECT * FROM info_user where user = ?";
        String user_user = user.getUser();
        String user_password = user.getPassword();

        List<InfoUser> ul= jdbcTemplate.query(sqlA, new Object[]{user_user}, (rs, rowNum) -> new InfoUser(
                rs.getString("user"),
                rs.getString("e_mail"),
                rs.getString("phone"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getString("token"),
                null
        ));
        InfoUser u;
        System.out.println((user_password));
        if (!ul.isEmpty()){
            u = ul.get(0);
            
            if (u.getPassword().equals(encryptThisString(user_password))){
                
                System.out.println("entraste"+ u.getPassword());
                
                if("ROLE_CLIENT".equals(u.getRole())){
                    System.out.println("entramos al rol");
                    String sqlC = "SELECT * FROM info_user natural join client where user = ?";
                    List<Client_Dto> ul2= jdbcTemplate.query(sqlC, new Object[]{user.getUser()}, (rs, rowNum) -> new Client_Dto(
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("e_mail"),
                            rs.getString("client_address"),
                            rs.getString("token")
                    ));

                    if(ul2.get(0)!=null)
                    {
                        
                        ul2.get(0).setUser(u.getUser());
                        
                        ul2.get(0).setRole(u.getRole());


                        return ul2.get(0);
                    }
                }
                if("ROLE_DOGWALKER".equals(u.getRole())){
                    String sqlP = "SELECT * FROM info_user natural join dog_walker where user = ?";
                    List<DogWalker_Dto> ul2= jdbcTemplate.query(sqlP, new Object[]{user.getUser()}, (rs, rowNum) -> new DogWalker_Dto(
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("e_mail"),
                            rs.getFloat("dog_walker_score"),
                            rs.getString("token")
                    ));
                    if(ul2.get(0)!=null)
                    {
                        
                       
                        ul2.get(0).setUser(u.getUser());
                        ul2.get(0).setRole(u.getRole());

                        return ul2.get(0);
                    }
                }
                if("ROLE_DOGDAYCARE".equals(u.getRole())){

                    String sqlG = "SELECT * FROM info_user natural join dog_daycare where user = ?";
                    List<DogDayCare_Dto> ul2= jdbcTemplate.query(sqlG, new Object[]{user.getUser()}, (rs, rowNum) -> new DogDayCare_Dto(
                            rs.getString("e_mail"),
                            rs.getString("dog_daycare_address"),
                            rs.getBoolean("dog_daycare_type"),
                            rs.getString("phone"),

                            rs.getFloat("dog_daycare_score"),

                            rs.getString("name"),
                            rs.getFloat("dog_daycare_base_price"),
                            rs.getFloat("dog_daycare_tax"),
                            rs.getString("token")
                    ));

                    if(ul2.get(0)!=null)
                    {
                       
                        ul2.get(0).setUser(u.getUser());
                        ul2.get(0).setRole(u.getRole());
                        return ul2.get(0);
                    }
                }
            }
        }
        return 0;
    }
}
