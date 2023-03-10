#include "bits/stdc++.h"

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    string input;
    cin >> input;

    vector<int> cnt = vector(26,0);

    for(char c : input){
        cnt[c-'a']++;
    }

    for (int i = 0; i < cnt.size(); ++i) {
        cout << cnt[i] << " ";
    }

    return 0;
}