module dyermccoy.agariogame {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;

  opens dyermccoy.agariogame to javafx.graphics;
  exports dyermccoy.agariogame;
}