package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserManagerTest {
  static UserManager userManager;

  @BeforeEach
  void テスト前処理() {
    userManager = UserManager.getInstance();
    userManager.deleteAllUser();
  }

  @Test
  void 正常系_UserManagerインスタンス同一() {
    UserManager userManagerHolder = UserManager.getInstance();
    assertThat(UserManager.getInstance()).isEqualTo(userManagerHolder);
  }

  @Test
  void 正常系_userList登録参照() {
    User user1 = new User("佐藤田中");
    User user2 = new User("仙台太郎");
    userManager.setUserToList(user1);
    userManager.setUserToList(user2);
    List<User> UserList = userManager.getUserList();
    assertThat(UserList).contains(user1, user2);
  }

  @Test
  void 正常系_userMap登録参照() {
    User user1 = new User("佐藤田中");
    User user2 = new User("仙台太郎");
    userManager.setUserToMap(user1);
    userManager.setUserToMap(user2);
    Map<String, User> userMap = userManager.getUserMap();
    assertThat(userMap).containsKeys("佐藤田中", "仙台太郎");
  }

  @Test
  void 正常系_user全削除() {
    User user1 = new User("佐藤田中");
    User user2 = new User("仙台太郎");
    userManager.setUserToList(user1);
    userManager.setUserToList(user2);
    userManager.setUserToMap(user1);
    userManager.setUserToMap(user2);
    userManager.deleteAllUser();
    assertThat(userManager.getUserList()).isEmpty();
  }

  @Test
  void 正常系_code指定user削除() {
    User user1 = new User("佐藤田中");
    User user2 = new User("仙台太郎");
    userManager.setUserToList(user1);
    userManager.setUserToList(user2);
    userManager.setUserToMap(user1);
    userManager.setUserToMap(user2);
    userManager.deleteUser("佐藤田中");
    assertThat(userManager.getUserList())
        .doesNotContain(user1)
        .contains(user2);
    assertThat(userManager.getUserMap())
        .doesNotContainKey(user1.getCode())
        .containsKeys(user2.getCode());
  }

  @Test
  public void 異常系_不可能() {
    User user1 = new User("佐藤田中");
    User user2 = new User("佐藤田中");
    userManager.setUserToList(user1);
    userManager.setUserToList(user2);
    userManager.setUserToMap(user1);
    userManager.setUserToMap(user2);
    userManager.deleteUser("佐藤田中");
    assertThat(userManager.getUserList()).isEmpty();
    assertThat(userManager.getUserMap()).isEmpty();
  }

}
