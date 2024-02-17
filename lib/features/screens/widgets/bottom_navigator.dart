import 'package:flutter/material.dart';
import '../../../constants.dart';


class BottomNavigator extends StatelessWidget {
  const BottomNavigator({
    super.key,
    required this.question,
    required this.action,
    required this.onPressed,
  });
  final String question;
  final String action;
  final Function() onPressed;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          Text(question),
          const SizedBox(
            width: 10,
          ),
          TextButton(
            onPressed: onPressed,
            child: Text(
              action,
              style: const TextStyle(
                  decoration: TextDecoration.underline,
                  fontSize: 14,
                  fontWeight: FontWeight.w700,
                  decorationColor: kSecondaryColor,
                  decorationThickness: 1.5),
            ),
          ),
        ],
      ),
    );
  }
}
