import 'package:flutter/cupertino.dart';

class ScaffoldUpperPart extends StatelessWidget {
  const ScaffoldUpperPart({super.key});

  @override
  Widget build(BuildContext context) {
    return  SizedBox(
      height: MediaQuery.of(context).size.height * 0.25,
      child: Image.asset(
        'assets/upper_frame.png',
        fit: BoxFit.cover,
      ),
    );
  }
}