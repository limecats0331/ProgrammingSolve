#include "bits/stdc++.h"

using namespace std;

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    string str;
    cin >> str;

    int total = 0;
    int left = 0;
    stack<char> S;

    for (char c: str) {
        if (c == '(') {
            left++;
        } else if (c == ')') {
            if (S.top() == '(') {
                left--;
                total += left;
            } else {
                left--;
                total++;
            }
        }
        S.push(c);
    }

    total += left;
    cout << total;

    return 0;
}