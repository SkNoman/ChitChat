import 'package:chit_chat/pages/frame/welcome/state.dart';
import 'package:get/get.dart';

class WelcomeController extends GetxController {
  WelcomeController();
  final title = "Chit Chat";
  final state = WelcomeState();

  void onReady(){
    super.onReady();
    print("Welcome Controller");
  }
}