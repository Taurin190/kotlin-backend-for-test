# Controllerのテストについて
以下参考にする。
https://github.com/mechero/spring-boot-testing-strategies
https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/

MockMvcは元々`spring-boot-starter-test`に入っているため、gradleに依存関係の追加は不要。

MockMvcでStandaloneでテストを行う場合には、
Springbootの仕組みを使ったテストでは無いためMockitoやMockKなどの仕組みでクラスを作成する必要がある。

## Tips
### MockKとは？
Kotlinの言語仕様に即した形で記述ができる。

### 全く依存関係の無いControllerについて
Controllerの初期化も普通にコンストラクタを呼ぶだけで大丈夫。
MockMvcのBuildersで追加すれば使える。

### RestControllerとControllerの内容のテスト
RestControllerの場合は返り値がそのまま出力されるため、以下のようなテストが書ける。

```kotlin
mockMvc.perform(
    get("/"))
    .andExpect(content().string(containsString("index")))
```

ただ、Controllerの場合には単体ではコンテンツを出力していないため、
Modelを確認する形になる。

```kotlin
mockMvc.perform(get("/mvc/test"))
    .andExpect(model().attribute("name", "test"))
```