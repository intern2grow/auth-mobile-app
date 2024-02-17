import 'package:flutter/material.dart';

class FieldEntry extends StatelessWidget {
  FieldEntry(
      {super.key,
      required this.label,
      required this.icon,
      required this.onPressed,
      required this.isPass});
  String label;
  Function()? onPressed;
  IconData icon;
  bool isPass;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 8),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            label,
            style: const TextStyle(color: Colors.grey),
          ),
          TextField(
            style: const TextStyle(
              fontSize: 5
            ),
            obscureText: isPass,
            decoration: InputDecoration(
              border:  OutlineInputBorder(
                borderRadius: BorderRadius.circular(8),
                borderSide: const BorderSide(
                  color: Color(0xff808194),
                  width: 0.5
                  )
              ),
              suffixIcon: IconButton(
                color: Colors.grey,
                onPressed: onPressed,
                icon: Icon(
                  icon,
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}
