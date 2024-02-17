

import 'package:flutter/cupertino.dart';

class ScaffoldLogo extends StatelessWidget {
  const ScaffoldLogo({super.key});

  @override
  Widget build(BuildContext context) {
    return Positioned(
      top: MediaQuery.of(context).size.height * 0.001,
      left: MediaQuery.of(context).size.width * 0.1,
      right: MediaQuery.of(context).size.width * 0.1,
      child: Image.asset('assets/logo.png'),
    );
  }
}
