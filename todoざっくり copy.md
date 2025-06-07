### **最終決戦：1週間スプリントToDoリスト**

#### **Day 1 (土): 環境構築とバックエンドの心臓部を作る**

**ゴール: DockerでSpring BootとPostgreSQLを立ち上げ、APIの骨格を完成させる。**

| チェック | タスク | 成果物 / 学習キーワード | 参考資料 (公式/記事/サンプル) | AI活用法 |
| :--- | :--- | :--- | :--- | :--- |
| `[ ]` | **プロジェクト作成**: Spring Boot (Web, JPA, Lombok, PostgreSQL Driver) | Spring Bootプロジェクトフォルダ | [**公式** Spring Initializr](https://start.spring.io/)\<br\>[**記事** Spring Boot入門](https://www.google.com/search?q=https://www.bold.ne.jp/engineer-blog/spring-boot-2)\<br\>[**サンプル** Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/) | - |
| `[ ]` | **Docker環境構築**: `Dockerfile`と`docker-compose.yml`を作成 | `Dockerfile`, `docker-compose.yml` | [**公式** Dockerfile reference](https://docs.docker.com/engine/reference/builder/)\<br\>[**記事** Spring Boot + PostgreSQLをDockerで](https://www.google.com/search?q=https://zenn.dev/kazuki_tam/articles/2ac2696020ce8c)\<br\>[**サンプル** Spring Boot with PostgreSQL and Docker Compose](https://www.baeldung.com/spring-boot-postgresql-docker) | 「Spring Boot + PostgreSQLを`docker-compose`で動かす最小構成の`Dockerfile`と`yml`ファイルを教えて」 |
| `[ ]` | **コンテナ起動確認**: `docker-compose up`で両方のコンテナがエラーなく起動する | 起動したDockerコンテナ | [**公式** docker-compose up](https://www.google.com/search?q=https://docs.docker.com/compose/reference/up/)\<br\>[**記事** docker-composeコマンド一覧](https://www.google.com/search?q=https://qiita.com/yuta_sasaki/items/934a4d2d4a2754e380bd) | - |
| `[ ]` | **API骨格実装**: `QuizController`に`GET /api/v1/quizzes`を作成し、**固定の文字列**（例: "Hello API"）を返すようにする | `curl`で文字列が返ってくるAPI | [**公式** Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)\<br\>[**記事** RestControllerでHello World](https://www.google.com/search?q=https://www.digitalocean.com/community/tutorials/spring-boot-rest-controller-beans-and-components-ja)\<br\>[**サンプル** Spring @RestController Example](https://www.baeldung.com/spring-new-requestmapping-shortcuts) | 「`@RestController`で単純な文字列を返す`Hello World`のコードを教えて」 |

-----

#### **Day 2 (日): データベースとの接続とデータ投入**

**ゴール: ContentfulのデータをDBに投入し、APIがDBのデータを返せるようにする。**

| チェック | タスク | 成果物 / 学習キーワード | 参考資料 (公式/記事/サンプル) | AI活用法 |
| :--- | :--- | :--- | :--- | :--- |
| `[ ]` | **Contentfulデータエクスポート**: CLIを使い`export.json`を取得 | `export.json`ファイル | [**公式** contentful-cli](https://www.contentful.com/developers/docs/tutorials/cli/import-and-export/)\<br\>[**記事** Contentful CLI の基本的な使い方](https://www.google.com/search?q=https://zenn.dev/not75743/articles/f3265a6f2b4c19) | 「`contentful-cli`のインストールとエクスポートコマンドを教えて」 |
| `[ ]` | **DB接続設定**: `application.properties`に`docker-compose`上のDB接続情報を記述 | `application.properties` | [**公式** Data Properties](https://www.google.com/search?q=https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html%23application-properties.data)\<br\>[**記事** Spring Boot + PostgreSQL 接続設定](https://www.google.com/search?q=https://www.sourceallies.com/2021/01/spring-boot-with-postgresql-and-docker-compose-ja/)\<br\>[**サンプル** Spring Boot with PostgreSQL](https://www.google.com/search?q=https://www.baeldung.com/spring-boot-postgresql) | 「Spring BootからDockerコンテナのPostgreSQLに接続するJDBC URLの書き方は？」 |
| `[ ]` | **EntityとRepository作成**: `Quiz`エンティティと`QuizRepository`インターフェースを作成 | `Quiz.java`, `QuizRepository.java` | [**公式** Spring Data JPA](https://spring.io/projects/spring-data-jpa)\<br\>[**記事** Spring Data JPAでCRUD](https://www.google.com/search?q=https://qiita.com/k-ta-1/items/b3c25b907106067b844f)\<br\>[**サンプル** Spring Boot Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/) | 「このJSON構造をJPAの`@Entity`にするにはどうすればいい？」 |
| `[ ]` | **データ投入**: `CommandLineRunner`で`export.json`を読み込み、DBに保存する処理を実装 | DBにデータが入ったPostgreSQL | [**公式** CommandLineRunner](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/CommandLineRunner.html)\<br\>[**記事** Spring Boot起動時に処理を実行](https://www.google.com/search?q=https://qiita.com/rubytomato%40github/items/169653a1e80973a985f4)\<br\>[**サンプル** Reading a JSON File in Java](https://www.google.com/search?q=https://www.baeldung.com/java-read-json-file) | 「JavaでJSONファイルを読み込んで、オブジェクトのリストに変換する方法は？」 |
| `[ ]` | **API改修**: `Controller`→`Service`→`Repository`の構造にし、DBから取得したデータを返すように修正 | DBから取得したJSONを返すAPI | [**記事** 3層アーキテクチャ(Controller/Service/Repository)とは](https://www.google.com/search?q=https://zenn.dev/yoshidaa/articles/ffb8235e16590b)\<br\>[**サンプル** Spring Boot REST API Example](https://www.google.com/search?q=https://www.javaguides.net/2021/08/spring-boot-rest-api-example-tutorial.html) | - |

-----

#### **Day 3 (月): 品質保証とドキュメントの自動化**

**ゴール: バックエンドのロジックをテストし、API仕様書を導入する。**

| チェック | タスク | 成果物 / 学習キーワード | 参考資料 (公式/記事/サンプル) | AI活用法 |
| :--- | :--- | :--- | :--- | :--- |
| `[ ]` | **Service層のユニットテスト**: `QuizService`の主要なロジックをJUnit5とMockitoでテスト | `QuizServiceTest.java` | [**公式** Spring Boot - Testing](https://www.google.com/search?q=https://docs.spring.io/spring-boot/docs/current/reference/html/features.html%23features.testing)\<br\>[**記事** Spring Boot + Mockitoでサービスクラスの単体テスト](https://www.google.com/search?q=https://zenn.dev/cti/articles/c00561584c4f9b)\<br\>[**サンプル** Testing in Spring Boot](https://www.baeldung.com/spring-boot-testing) | 「`Mockito`を使ってRepositoryをモック化し、Serviceをテストするコードの雛形を教えて」 |
| `[ ]` | **Swagger導入**: Swagger(springdoc-openapi)を導入し、API仕様書を自動生成 | `localhost:8080/swagger-ui.html` | [**公式** springdoc-openapi](https://springdoc.org/)\<br\>[**記事** Spring Boot 3 + Springdoc OpenAPI v2 でAPI仕様書を自動生成](https://www.google.com/search?q=https://qiita.com/k-ta-1/items/4b254e4f20813f890333)\<br\>[**サンプル** Spring Boot REST API Documentation with Swagger](https://www.google.com/search?q=https://www.javaguides.net/2021/08/spring-boot-swagger-3-example.html) | 「Spring Boot 3.xに`springdoc-openapi`を導入する`build.gradle`の記述と、基本的な設定方法は？」 |

-----

#### **Day 4 (火): バックエンドの公開とCIの導入**

**ゴール: バックエンドをインターネットに公開し、GitHub Actionsでテストを自動化する。**

| チェック | タスク | 成果物 / 学習キーワード | 参考資料 (公式/記事/サンプル) | AI活用法 |
| :--- | :--- | :--- | :--- | :--- |
| `[ ]` | **Herokuへコンテナデプロイ**: Day1で作成した`Dockerfile`を使い、Herokuにデプロイ | **デプロイされたAPIの公開URL** | [**公式** Heroku - Container Registry & Runtime](https://devcenter.heroku.com/articles/container-registry-and-runtime)\<br\>[**記事** Spring BootアプリをDocker経由でHerokuにデプロイ](https://www.google.com/search?q=https://qiita.com/suke_masa/items/675317796a798b3c9597)\<br\>[**サンプル** Deploying Spring Boot Applications to Heroku](https://www.google.com/search?q=https://www.baeldung.com/deploy-spring-boot-app-to-heroku) | 「`Dockerfile`を使ってHerokuにデプロイする手順の要点を教えて」 |
| `[ ]` | **CI導入**: GitHub Actionsでpush時に`./gradlew test`が自動実行されるワークフローを作成 | `ci.yml`ファイル | [**公式** Building and testing Java with Gradle](https://docs.github.com/ja/actions/automating-builds-and-tests/building-and-testing-java-with-gradle)\<br\>[**記事** GitHub ActionsでGradleのCI環境を構築](https://www.google.com/search?q=https://zenn.dev/masarakki/articles/a77a4aa219e2c6)\<br\>[**サンプル** GitHub Actions for Spring Boot with Gradle](https://www.google.com/search?q=https://www.baeldung.com/github-actions-java-spring-boot) | 「GitHub ActionsでJava/Gradleプロジェクトのテストを自動実行する、一番シンプルな`yml`は？」 |
| `[ ]` | **Androidプロジェクトの準備**: Contentful関連のコードを削除し、Retrofit/Hiltの依存関係を追加 | 改修準備ができたAndroidプロジェクト | [**公式** Retrofit](https://square.github.io/retrofit/) / [**公式** Hilt](https://developer.android.com/training/dependency-injection/hilt-android)\<br\>[**記事** Hiltの基本的な使い方](https://www.google.com/search?q=https://zenn.dev/reza_q/articles/a45a337e75344c) | - |

-----

#### **Day 5 (水): Androidとバックエンドの接続**

**ゴール: Androidアプリが、インターネット経由で自作APIからデータを取得して表示する。**

| チェック | タスク | 成果物 / 学習キーワード | 参考資料 (公式/記事/サンプル) | AI活用法 |
| :--- | :--- | :--- | :--- | :--- |
| `[ ]` | **Android通信層の実装**: `ApiService`インターフェースとHiltの`NetworkModule`を作成 | `ApiService.kt`, `NetworkModule.kt` | [**公式** Hiltを使用した依存関係の注入](https://developer.android.com/training/dependency-injection/hilt-android)\<br\>[**記事** Retrofit2+HiltのDI対応](https://www.google.com/search?q=https://qiita.com/Riscait/items/e889b7c250269f8c05c3)\<br\>[**サンプル** Retrofit with Hilt for Dependency Injection](https://www.google.com/search?q=https://www.geeksforgeeks.org/retrofit-with-hilt-for-dependency-injection-in-android/) | 「Retrofitの`Builder`でベースURLやコンバーターを設定するコードの例は？」 |
| `[ ]` | **データフローの修正**: `Repository`と`ViewModel`を修正し、Heroku上のAPIを叩くように変更 | - | [**公式** ViewModel-Repository-Coroutine](https://www.google.com/search?q=https://developer.android.com/jetpack/guide/data-layer)\<br\>[**記事** ViewModel, LiveData, Coroutineで非同期処理](https://www.google.com/search?q=https://qiita.com/Mocacamo/items/325785cf83d0628e932c)\<br\>[**サンプル** Making Network Calls with Coroutines](https://www.google.com/search?q=https://proandroiddev.com/making-network-calls-with-coroutines-bf92b8ba373d) | 「Kotlin Coroutinesで非同期にAPIを呼び出し、結果を`LiveData`に反映させるコードのパターンを教えて」 |
| `[ ]` | **動作確認**: アプリを起動し、Heroku上のAPIからクイズデータが表示されることを確認 | **自作APIと通信して動くアプリ** | [**記事** Logcatでログを表示](https://www.google.com/search?q=https://developer.android.com/studio/debug/logcat%3Fhl%3Dja)\<br\>[**サンプル** Handling Retrofit Errors](https://www.google.com/search?q=https://www.baeldung.com/retrofit-error-handling) | エラーが出た時に「このエラーログ（貼り付け）の根本原因として考えられる可能性をリストアップして」 |

-----

#### **Day 6 (木): ドキュメント作成**

**ゴール: 第三者がリポジトリを見て、一目でプロジェクトの価値と構造を理解できる状態にする。**

| チェック | タスク | 成果物 / 学習キーワード | 参考資料 (公式/記事/サンプル) | AI活用法 |
| :--- | :--- | :--- | :--- | :--- |
| `[ ]` | **`README.md`作成**: プロジェクト概要、**システム構成図(Mermaid)**、**ER図(Mermaid)** を記述 | `README.md` | [**公式** Mermaid](https://mermaid.js.org/intro/)\<br\>[**記事** GitHubでMermaidを使ってER図やシーケンス図を作成する](https://www.google.com/search?q=https://zenn.dev/y_suzu/articles/f39c33957d4289)\<br\>[**サンプル** Mermaid Live Editor](https://mermaid.live/) | 「このシステム構成をMermaid記法で図にして」 |
| `[ ]` | **`README.md`に各種情報を追記**: APIの公開URL、Swagger URL、ローカルでの実行方法(`docker-compose up`)などを記述 | `README.md` | [**記事** プロジェクトの「顔」としてのREADME](https://www.google.com/search?q=https://zenn.dev/hatena/articles/good-readme-for-your-product) | - |
| `[ ]` | **技術選定理由のドキュメント化**: `docs/architecture.md`ファイルに、なぜSpring Boot/Docker/Retrofit等を選んだのかを簡潔に記述 | `architecture.md` | [**記事** アーキテクチャデシジョンレコード(ADR)とは](https://www.google.com/search?q=https://qiita.com/takasp/items/72e763b020612c33221b)\<br\>[**サンプル** ADR GitHub repository](https://github.com/joelparkerhenderson/architecture-decision-record) | 「技術選定の理由を書く時、どんな観点（学習コスト、将来性、エコシステムなど）で書くと良いか、構成案を教えて」 |

-----

#### **Day 7 (金): 最終調整とアウトプット準備**

**ゴール: 全体の最終チェックを行い、学習の証跡となる技術記事の骨子を完成させる。**

| チェック | タスク | 成果物 / 学習キーワード | 参考資料 (公式/記事/サンプル) | AI活用法 |
| :--- | :--- | :--- | :--- | :--- |
| `[ ]` | **最終テスト**: AndroidアプリとバックエンドAPIの全体を通した動作確認 | - | - | - |
| `[ ]` | **リポジトリの清掃**: 不要なファイルやコメントを削除 | **完成されたGitHubリポジトリ** | [**記事** Gitでコミットを取り消す、やり直す](https://www.google.com/search?q=https://qiita.com/sinyo-s/items/944c33833d906a2f7c22) | - |
| `[ ]` | **Qiita/Zenn記事の骨子作成**: この1週間の挑戦を記事にするための構成（タイトル、見出し、書きたいことの箇条書き）を作成 | 記事の構成メモ | [**記事** 受ける技術記事の書き方](https://www.google.com/search?q=https://zenn.dev/fujii/articles/writing-technical-articles)\<br\>[**記事** 読まれる技術記事を書くための5つのポイント](https://www.google.com/search?q=https://blog.findy-teams.com/posts/how-to-write-a-tech-blog-that-gets-read) | 「『未経験から1週間でフルスタックアプリをDocker化してデプロイした話』というテーマで、読者が最も興味を持つであろう記事の構成案を3つ提案して」 |
| `[ ]` | **模擬面接の準備**: プロジェクト説明の練習（1分、3分、5分バージョン）と、想定問答の準備 | 面接のトークスクリプト | [**記事** ポートフォリオ面接対策！](https://www.google.com/search?q=https://qiita.com/Genki_ozaki/items/f07c2136e0996841793a) | - |
| `[ ]` | **休息** | **やりきった自信と達成感** | - | - |

-----
