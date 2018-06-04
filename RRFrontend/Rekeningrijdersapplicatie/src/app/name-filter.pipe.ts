import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'nameFilter'
})
export class NameFilterPipe implements PipeTransform {

  transform(items: any, name: string, license: string, fuel: string){
    if (items && items.length){
      return items.filter(item =>{
        if (name && item.name.toLowerCase().indexOf(name.toLowerCase()) === -1){
          return false;
        }
        console.log(license == undefined);
        if (license && item.licenseplate.toLowerCase().indexOf(license.toLowerCase()) === -1){
          return false;
        }
        if (fuel && item.classification.toLowerCase().indexOf(fuel.toLowerCase()) === -1){
          return false;
        }
        return true;
      })
    }
    else{
      return items;
    }
  }

}
