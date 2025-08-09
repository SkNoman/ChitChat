import 'package:chit_chat/pages/frame/welcome/controller.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class WelcomePage extends GetView<WelcomeController> {
  const WelcomePage({super.key});

  Widget _buildPageHeadTitle(String title){
    return  Container(
      child: Text(
        title,
        style: TextStyle(
          color: AppColors.primaryElementText,
          fontFamily: "Montserrat",
          fontWeight: FontWeight.bold,
          fontSize: 45
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: _buildPageHeadTitle(title),
      ),
    );
  }
}
