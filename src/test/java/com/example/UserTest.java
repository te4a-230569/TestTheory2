package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserTest {
  static User user = null;

  @BeforeAll
  static void テスト前処理() {
    user = new User("TestUser");
  }

  @Test
  void 正常系_ユーザー管理コード登録参照() {
    user.setCode("75967");
    assertThat(user.getCode()).isEqualTo("75967");
  }
  @Test
  void 正常系_名前登録参照() {
    user.setName("田中佐藤");
    assertThat(user.getName()).isEqualTo("田中佐藤");
  }
  @Test
  void 正常系_年齢登録参照() {
    user.setAge(128);
    assertThat(user.getAge()).isEqualTo(128);
  }
  @Test
  void 異常系_範囲外年齢登録() {
    user.setAge(2026);
    assertThat(user.getAge()).isEqualTo(-1);
  }
  @Test
  void 異常系_青薔薇() {
    assertThat(user.getAge()).isEqualTo(-1);
  }

  @AfterAll
  static void テスト後処理() {
    user = null;
  }
}