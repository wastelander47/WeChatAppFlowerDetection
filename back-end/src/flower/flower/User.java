package flower.flower;

public class User {
private String userName;
private String passWord;
private char sex;
private String hobby;
public String getUserName() {
return userName;
}
public void setUserName(String userName) {
this.userName = userName;
}
public String getPassWord() {
return passWord;
}
public void setPassWord(String passWord) {
this.passWord = passWord;
}
public char getSex() {
return sex;
}
public void setSex(char sex) {
this.sex = sex;
}
public String getHobby() {
return hobby;
}
public void setHobby(String hobby) {
this.hobby = hobby;
}
public User(String userName, String passWord, char sex,
String hobby) {
super();
this.userName = userName;
this.passWord = passWord;
this.sex = sex;
this.hobby = hobby;
}
@Override
public String toString() {
// TODO Auto-generated method stub
return this.userName+","+this.passWord+","
+this.sex+","+this.hobby;
}

}